import React from "react";

export const Login = () =>{
    const githubLogin = () =>{
        window.location.href = "http://localhost:8080/login"
    }
    return(
        <div>
            <button onClick={githubLogin}>Login with Github</button>
        </div>

    )
}