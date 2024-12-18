import { Outlet, Navigate } from "react-router-dom";

const ProtectedRoutes = () =>{
    const authenticated = true
    return authenticated ? <Outlet/> : <Navigate to = "/login"/>

}

export default ProtectedRoutes