import {useAxios} from '../utility/axiosUtils'
import { UserLoginContext, UserLoginContextType } from '../ContextAPI/UserLoginContext'
import { useContext , useEffect , useState } from 'react'
import { useNavigate } from 'react-router-dom'

export const Login = () =>{



    // const {response, loading  , error , fetchData} = useAxios()
    // const {userDetails , setUserDetails , userDetailsError , setUserDetailsError} = useContext(UserLoginContext) as UserLoginContextType


    const [token ,setToken] = useState<string | null>(null) 
    const navigate = useNavigate()



    const githubLogin = () =>{
        window.location.href = "http://localhost:8080/oauth2/authorization/github" 
    }

    



    
    return(
        <div>
            <button onClick={() => {githubLogin() }}>Login with github</button>
        </div>
    )
}