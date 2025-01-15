import { HeroSectionBeforeAuth } from "../HeroSection/HeroSectionBeforeAuth"
import {NavbarComponent} from "../Navbar/Navbar"

export const HomeBeforeAuth = () =>{
    
    return(
      <div className='h-screen flex flex-col '>
        
            <NavbarComponent  />
            <HeroSectionBeforeAuth/>
            
        </div>
    )
}