import { Login } from "./AuthComponents/Login"
import {Home} from "./components/Home"
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import {DashBoard} from "./components/DashBoard"
import ProtectedRoutes from "./ProtectedRoutes/ProtectedRoutes"
import { Logout } from "./AuthComponents/Logout"


function App() {

  return (
    <>
    <Router>
      <Routes>
        
      <Route path = "/" element = {<Home/>}/>
      <Route path = "/login" element = {<Login/>} />


<Route element = {<ProtectedRoutes/>}>
<Route path = "/dashboard" element = {<DashBoard/>}/> 
<Route path = "/logout" element = {<Logout/>}/> 
</Route> 


    </Routes>
    </Router>
   
  
    
    </>
  )
}

export default App
