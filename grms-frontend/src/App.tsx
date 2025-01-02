//Library imports
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import{QueryClient, QueryClientProvider} from '@tanstack/react-query'

//Component imports
import  Login  from "./components/AuthComponents/Login"
import {Home} from "./components/HomeComponents/Home"
import {DashBoard} from "./components/DashBoard"
import ProtectedRoutes from "./ProtectedRoutes/ProtectedRoutes"
import { Logout } from "./components/AuthComponents/Logout"



function App() {

  const queryClient = new QueryClient()



  return (
    <>
    
    <QueryClientProvider client = {queryClient}>
    <Router>
      <Routes>
        
      <Route path = "/" element = {<Home/>}/>
      <Route path = "/login" element = {<Login/>} />


{/* Protected Routes */}
        <Route element = {<ProtectedRoutes/>}>
        <Route path = "/dashboard" element = {<DashBoard/>}/> 
        <Route path = "/logout" element = {<Logout/>}/> 
        </Route> 
{/* Protected Routes */}


    </Routes>
    </Router>
    </QueryClientProvider>
    
   
  
    
    </>
  )
}

export default App
