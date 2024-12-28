    package com.Barsat.Github.Repository.Management.Service.TreeServices;

    import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
    import com.Barsat.Github.Repository.Management.Models.ResponseModels.TreeStructureResponse;
    import com.Barsat.Github.Repository.Management.Nodes.Node;
    import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
    import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
    import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.util.*;

    @Service
    public class TreeService {

        private final GetRepoSHAKey getRepoSHAKey;
        private final OAuthService oAuthService;
        private final GithubReposRepository githubReposRepository;
        private final GetAuthenticatedUserName getAuthenticatedUserName;



        RestTemplate restTemplate = new RestTemplate();

        public TreeService(GetRepoSHAKey getRepoSHAKey , OAuthService oAuthService, GithubReposRepository githubReposRepository , GetAuthenticatedUserName getAuthenticatedUserName) {
            this.getRepoSHAKey = getRepoSHAKey;
            this.oAuthService = oAuthService;
            this.githubReposRepository = githubReposRepository;
            this.getAuthenticatedUserName = getAuthenticatedUserName;
        }

        public List<String> getTreeString(Integer repoId) {


            GithubRepoEntity githubRepoEntity = githubReposRepository.findById(repoId).orElse(null);

            String RepoName = githubRepoEntity.getName();
            String username = getAuthenticatedUserName.getUsername();

            //give sha key method reponame and username to get the right tree's sha key
            String SHAKey = getRepoSHAKey.getSHA(RepoName , username);

            String url = "https://api.github.com/repos/"+ username+ "/"+RepoName+"/git/trees/"+SHAKey + "?recursive=1";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github+json");
            headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange( url , HttpMethod.GET, entity, String.class);
            return Arrays.asList(response.getBody() , RepoName);
        }

        public String getTree(Integer repoId) {
            List<String> treeStringStructure = getTreeString(repoId);

            String treeString = treeStringStructure.get(0);
            String repoName = treeStringStructure.get(1);

            TreeStructureResponse treeStructureResponse;

            ObjectMapper mapper = new ObjectMapper();
            try {
                treeStructureResponse = mapper.readValue(treeString, TreeStructureResponse.class);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }


            //parent node with repository name and null parent , it is always a directory
            Node parentNode = new Node(repoName , null , true);

            /*
            to store filenames so that there is no duplicate. After iterating a path like eg- git/barsat/src , i store every git , barsat and src but if i come
            through same path again to store a different file git/barsat/src/tutorial.java then i wont add git barsat and src again. This helps not
            create new node everytime.
            */
            Set<String> fileNames = new HashSet<>();

            //TreeStructureResponse tree type iterating tree[] of treeStructureResponse
            for(TreeStructureResponse.tree tree :treeStructureResponse.getTree()) {

                //if the path inside tree does not contain '/' then it is the first path and if it equals blobs then it has no more directory ,so false for directory
                if (!tree.getPath().contains("/") && tree.getType().equals("blob")) {

                    //Add that path and create new node for that path
                    Node blobNodes = new Node(tree.getPath(), parentNode , false);

                    // add that first iteration node to parent node (addChildrenToParent) is defined in my node class.
                    parentNode.addChildrenToParent(parentNode,blobNodes);

                    //add this name to fileNames so that duplicate is not created. Not so useful for blob type but regardless we add.
                    fileNames.add(tree.getPath());

                }

                else if (!tree.getPath().contains("/") && tree.getType().equals("tree")) {

                    Node continuingNode = new Node(tree.getPath(), parentNode , true);
                    parentNode.addChildrenToParent(parentNode,continuingNode);

                    //since this is a directory path , it is important to add it to fileNames because it will reoccur.
                    fileNames.add(tree.getPath());
                }

                // else case if tree.getPath contains '/' , it needs to be iterated and done accordingly.
                else{

                    /*  get all the paths related on list of strings , if git/barsat/src then git , barsat and src will come. This way i can track because barsat
                    is inside git and src is inside barsat.
                    */
                    List<String> split = Arrays.asList(tree.getPath().split("/"));

                    /* start iterating split list from 1 because '0' : the first path is already recorded upside on !tree.getPath().contains("/").
                    A node for the first path is always already created. We just need to start creating node from the second path from this loop */
                    for (int i=1 ; i < split.size() ; i++) {

                        //if fileNames does not contain the current iteration of split then there is no node for it. Make loop to create it
                        if(!fileNames.contains(split.get(i))){

                            // i was first suprised when this worked lol.
                            Node ParentNode = parentNode.accessAnyNode(split.get(i - 1));

                            //new node if filename does not contain it. Parent node is always one position beofe it , access that node.
                            Node node = new Node(split.get(i), ParentNode , tree.getType() == "tree");

                            //add this children node to parent node (one position previous node)
                            ParentNode.addChildrenToParent(ParentNode,node);

                            //add the /path to filename
                            fileNames.add(split.get(i));

                        }



                    }

//                    System.out.println(split);



                }
            }



            System.out.println(parentNode.toStringHelper(parentNode,8));
            return treeStructureResponse.toString();
        }




    }