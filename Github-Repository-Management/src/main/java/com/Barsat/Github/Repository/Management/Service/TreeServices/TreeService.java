    package com.Barsat.Github.Repository.Management.Service.TreeServices;

    import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
    import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
    import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
    import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

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

        public String getTree(Integer repoId) {


            GithubRepoEntity githubRepoEntity = githubReposRepository.findById(repoId).orElse(null);

            String RepoName = githubRepoEntity.getName();
            String SHAKey = getRepoSHAKey.getSHA();
            String username = getAuthenticatedUserName.getUsername();

            String url = "https://api.github.com/repos/"+ username+ "/"+RepoName+"/git/trees/"+SHAKey;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github+json");
            headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange( url , HttpMethod.GET, entity, String.class);
            return response.getBody();
        }




    }