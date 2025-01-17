import { FAQSection } from "../HeroSection/FAQSection"
import { HeroSectionBeforeAuth } from "../HeroSection/HeroSectionBeforeAuth"
import { ThenSection } from "../HeroSection/ThenSection"
import {NavbarComponent} from "../Navbar/Navbar"
import { Footer } from "../HeroSection/Footer"

export const HomeBeforeAuth = () =>{
    
    return(
      <div className='h-screen flex flex-col '>
        
            <NavbarComponent  />
            <HeroSectionBeforeAuth/>
            <ThenSection/>
            <FAQSection/>
            <Footer/>
            
            
            
        </div>
    )
}