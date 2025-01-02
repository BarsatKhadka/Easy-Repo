interface propType{
    username: string
}

export const HeroSectionAfterAuth = (props : propType) =>{
    return(
        <>
        <div >
        <div className="grid grid-cols-4 grid-rows-2 gap-8">
  <div key="item-1" className="col-start-1 row-start-1 col-span-3 row-span-2">
  <p><img src={`https://ghchart.rshah.org/${props?.username}`} alt="User's contribution chart" /></p>
  </div>
  <div key="item-2" className="col-start-4 row-start-1 col-span-1 row-span-2">
    1(2)
  </div>
</div>
         

         
         </div>
         
        </>
    )
}