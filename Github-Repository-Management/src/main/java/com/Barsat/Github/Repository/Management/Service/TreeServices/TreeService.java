package com.Barsat.Github.Repository.Management.Service.TreeServices;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.TreeStructureResponse;
import com.Barsat.Github.Repository.Management.Nodes.Node;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetFormattedURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@Service
public class TreeService {

    private final GetRepoSHAKey getRepoSHAKey;
    private final OAuthService oAuthService;
    private final GithubReposRepository githubReposRepository;
    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final GetFormattedURL getFormattedURL;



    RestTemplate restTemplate = new RestTemplate();

    public TreeService(GetRepoSHAKey getRepoSHAKey , OAuthService oAuthService, GithubReposRepository githubReposRepository ,
                       GetAuthenticatedUserName getAuthenticatedUserName , GetFormattedURL getFormattedURL

    ) {
        this.getRepoSHAKey = getRepoSHAKey;
        this.oAuthService = oAuthService;
        this.githubReposRepository = githubReposRepository;
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.getFormattedURL = getFormattedURL;
    }

    public List<String> getTreeString(Integer repoId) {


        GithubRepoEntity githubRepoEntity = githubReposRepository.findById(repoId).orElse(null);

        String RepoName = githubRepoEntity.getName();
        String RepoUrl = githubRepoEntity.getHtml_url();
        String RepoBranch = githubRepoEntity.getDefault_branch();
        String username = getAuthenticatedUserName.getUsername();

        //give sha key method reponame and username to get the right tree's sha key
        String SHAKey = getRepoSHAKey.getSHA(RepoName , username);

        String url = "https://api.github.com/repos/"+ username+ "/"+RepoName+"/git/trees/"+SHAKey + "?recursive=1";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange( url , HttpMethod.GET, entity, String.class);
        return Arrays.asList(response.getBody() , RepoName , RepoUrl , RepoBranch);
    }

    public Node getTree(Integer repoId) {
        List<String> treeStringStructure = getTreeString(repoId);

        String treeString = treeStringStructure.get(0);

        //all details collected to form a individual files url because github doesnot provide that.
        String repoName = treeStringStructure.get(1);
        String repoUrl = treeStringStructure.get(2);
        String repoBranch = treeStringStructure.get(3);

        TreeStructureResponse treeStructureResponse;

        ObjectMapper mapper = new ObjectMapper();
        try {
            treeStructureResponse = mapper.readValue(treeString, TreeStructureResponse.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }




        //parent node with repository name and null parent , it is always a directory
        Node parentNode = new Node(repoName , null , true , repoName, repoName , null);




            /*
            to store filenames so that there is no duplicate. After iterating a path like eg- git/barsat/src , i store every git , barsat and src but if i come
            through same path again to store a different file git/barsat/src/tutorial.java then i wont add git barsat and src again. This helps not
            create new node everytime.
            */
        Set<String> fileNames = new HashSet<>();

        //TreeStructureResponse tree type iterating tree[] of treeStructureResponse
        for(TreeStructureResponse.tree tree :treeStructureResponse.getTree()) {

            String baseUrl = repoUrl + "/" + "tree" + "/" + repoBranch + "/";

            //if it doesnot contain '/' then it is the first child the node is made for them before anything.
            if(!tree.getPath().contains("/")){

                String rawUrl =  baseUrl + tree.getPath();

                //constructing individual url for every path.
                String formattedUrl = getFormattedURL.getURL(rawUrl);

                Node firstChildNode = new Node(tree.getPath()+tree.getPath(), parentNode, tree.getType().equals("tree") , tree.getPath() , tree.getPath() , formattedUrl);
                parentNode.addChildrenToParent(firstChildNode);
                fileNames.add(tree.getPath());
            }


            // else case if tree.getPath contains '/' , it needs to be iterated and done accordingly.
            else{

                String rawUrl =  baseUrl + tree.getPath();
                String formattedUrl = getFormattedURL.getURL(rawUrl);



                    /*  get all the paths related on list of strings , if git/barsat/src then git , barsat and src will come. This way i can track because barsat
                    is inside git and src is inside barsat.
                    */
                List<String> split = Arrays.asList(tree.getPath().split("/"));

                    /* start iterating split list from 1 because '0' : the first path is already recorded upside on !tree.getPath().contains("/").
                    A node for the first path is always already created. We just need to start creating node from the second path from this loop */
                for (int i=1 ; i < split.size() ; i++) {

                    //if fileNames does not contain the current iteration of split then there is no node for it. Make loop to create it
                    if(!fileNames.contains(split.get(i) + split.get(0))){


                        // i was first suprised when this worked lol.
                        //adding the get(0) [first directory name] so that two different parent nodes with same filename not get jumbled.
                        Node ParentNode = parentNode.accessAnyNode(split.get(i - 1)+split.get(0));

                        //new node if filename does not contain it. Parent node is always one position beofe it , access that node.
                        Node node = new Node(split.get(i)+split.get(0), ParentNode , tree.getType().equals("tree") , tree.getPath() , split.get(i) , formattedUrl);

                        //add this children node to parent node (one position previous node)
                        ParentNode.addChildrenToParent(node);

                        //add the /path to filename
                        fileNames.add(split.get(i)+ split.get(0));

                    }





                }


//                    System.out.println(split);



            }
        }



        return parentNode;

    }





}