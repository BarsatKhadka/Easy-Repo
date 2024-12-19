import axios from 'axios'
import { useNavigate } from 'react-router-dom'

export const Logout = () =>{

    const navigate = useNavigate()

    const logoutFunction = async() =>{
        try{
       await axios.post('http://localhost:8080/logout' , {}, {withCredentials: true})
       sessionStorage.removeItem('authenticated')
        }

        catch(error){

        }
    }

    return (
        <>
        <button onClick = {logoutFunction}>Logout </button>

        </>
    )
}