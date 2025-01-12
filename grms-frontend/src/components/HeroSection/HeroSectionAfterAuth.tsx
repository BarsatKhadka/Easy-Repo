import GitHubCalendar from "react-github-calendar"

interface propType{
    username: string
}

export const HeroSectionAfterAuth = (props : propType) =>{
    return(
        <>
        <div>
  <div key="item-1" className="col-start-1 row-start-1 col-span-3 mb-3 ">
  <GitHubCalendar username={props.username}/>
  </div>
  
</div>
         

      
         
        </>
    )
}