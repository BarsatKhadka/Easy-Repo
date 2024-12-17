import { Login } from "./components/Login"
import {Home} from "./components/Home"
import {useQuery , QueryClient , QueryClientProvider , useQueryClient} from "@tanstack/react-query"
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import {DashBoard} from "./components/DashBoard"
import {useState} from 'react'

function App() {

  
  const queryClient = new QueryClient();

  const [userDetails , setUserDetails] = useState<any>(null)
  const [userDetailsError , setuserDetailsError] = useState<any>(null)
  



  return (
    <>
    <QueryClientProvider client = {queryClient}>

  

    <Router>
      <Routes>
    <Route path = "/" element = {<Home/>}/>
    <Route path = "/dashboard" element = {<DashBoard/>}/>
    <Route path = "/login" element = {<Login/>} />
    </Routes>
    </Router>

    </QueryClientProvider>
    
    
    </>
  )
}

export default App
