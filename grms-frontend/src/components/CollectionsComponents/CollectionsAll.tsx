interface allCollectionPropType{
    allCollection: any;

}

//allCollection prop is coming from CollectionsMain.
export const CollectionsAll = ({allCollection}: allCollectionPropType) =>{

    console.log(allCollection)

   
    

    return (
        <>

        <p>This is collections ALl</p>
        <p>{allCollection?.collectionId}</p>
       
        </>

    )
}