import { Login } from "./components/Login"
import {Home} from "./components/Home"
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import {DashBoard} from "./components/DashBoard"
import ProtectedRoutes from "./ProtectedRoutes/ProtectedRoutes"


function App() {

  return (
    <>
    <Router>
      <Routes>
      <Route path = "/" element = {<Home/>}/>
      <Route path = "/login" element = {<Login/>} />
<Route element = {<ProtectedRoutes/>}>
<Route path = "/dashboard" element = {<DashBoard/>}/> </Route> 
    </Routes>
    </Router>
   
  
    
    </>
  )
}

export default App
