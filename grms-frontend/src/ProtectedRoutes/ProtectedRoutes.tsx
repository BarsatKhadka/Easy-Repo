import { Outlet, Navigate } from "react-router-dom";


// Use the defined type for the props
const ProtectedRoutes = () => {
  let authenticated = false
    if(sessionStorage.getItem('authenticated') == 'True'){
      authenticated = true
    }
  return authenticated ? <Outlet /> : <Navigate to="/login" />;
};

export default ProtectedRoutes;
