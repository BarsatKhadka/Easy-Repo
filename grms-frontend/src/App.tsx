import { Login } from "./components/Login"
import {Home} from "./components/Home"
import {useQuery , QueryClient , QueryClientProvider , useQueryClient} from "@tanstack/react-query"
import {BrowserRouter as Router , Routes , Route} from 'react-router-dom'
import {DashBoard} from "./components/DashBoard"
import { UserLoginContext } from "./ContextAPI/UserLoginContext"
import {useState} from 'react'

function App() {

  
  const queryClient = new QueryClient();

  const [userDetails , setUserDetails] = useState("")
  const [userDetailsError , setUserDetailsError] = useState(null)
  



  return (
    <>
    <QueryClientProvider client = {queryClient}>
      <UserLoginContext.Provider value={{userDetails, setUserDetails , userDetailsError , setUserDetailsError}}>
  

    <Router>
      <Routes>
    <Route path = "/" element = {<Home/>}/>
    <Route path = "/dashboard" element = {<DashBoard/>}/>
    <Route path = "/login" element = {<Login/>} />
    </Routes>
    </Router>

    </UserLoginContext.Provider>
    </QueryClientProvider>
    
    
    </>
  )
}

export default App
