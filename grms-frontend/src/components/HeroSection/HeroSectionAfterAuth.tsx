interface propType{
    username: string
}

export const HeroSectionAfterAuth = (props : propType) =>{
    return(
        <>
        <div >
         <img src={`https://ghchart.rshah.org/${props?.username}`} alt="User's contribution chart" />
         </div>
         
        </>
    )
}