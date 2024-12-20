import axios from 'axios'


export const CreateCollections = () =>{

    const creatingCollect = async() =>{
        try{
            const response = await axios.post("http://localhost:8080/easyrepo/collections/createCollection", {'collectionName': 'naya' , 'collectionDescription': 'wala' , 'githubRepoIds': [2,3,4,5]}, 
            {withCredentials: true , headers : {'X-CSRF-TOKEN': sessionStorage.getItem('csrf') }})
            console.log('done boss')
        }
        catch(error){

        }

    }

    return(
        <>
        <div className="mt-4">
            <p>Creating collections</p>
            <button onClick={creatingCollect}>Create collection</button>

        </div>
        </>
    )
}