

export const Login = () =>{

    const githubLogin = () =>{
        window.location.href = "http://localhost:8080/oauth2/authorization/github" 
        
    }
    return(
        <div>
            <button onClick={() => {githubLogin() }}>Login with github</button>
        </div>
    )
}