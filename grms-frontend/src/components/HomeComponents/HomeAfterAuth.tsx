import { CollectionsMain } from '../CollectionsComponents/CollectionsMain';
import {Tags} from '../TagComponents/Tags'

export const HomeAfterAuth = () =>{
    return(
        <>
        <p>This is home after authentication</p>
        <CollectionsMain/> 
        <Tags/>
        
        

        </>
    )
}