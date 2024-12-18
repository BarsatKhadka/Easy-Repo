import { Login } from "./components/Login"
import {Home} from "./components/Home"
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import {DashBoard} from "./components/DashBoard"
import ProtectedRoutes from "./ProtectedRoutes/ProtectedRoutes"
import { useUserStore } from "./store/UserStore"


function App() {

  const {authenticated,setAuthenticated} = useUserStore()

  return (
    <>
    {authenticated ? "Authenticated" : "Not authenticated"}
    <Router>
      <Routes>
      <Route path = "/" element = {<Home/>}/>
      <Route path = "/login" element = {<Login/>} />

    <Route element = {<ProtectedRoutes/>}>
    <Route path = "/dashboard" element = {<DashBoard/>}/>
    </Route>


    </Routes>
    </Router>
   
  
    
    </>
  )
}

export default App
