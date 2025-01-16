import CreateCollection from "../../../src/assets/images/CreateCollections.png"
export const ThenSection = () =>{
    return(
<>
<div className="bg-white">
<div className="mt-4 flex justify-center items-center  flex-col items-start space-y-8 px-4 sm:px-8 md:px-12 lg:px-16 ">
<span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl md:text-4xl lg:text-5xl font-bold">
    Then?   
</span>
</div>
<div className="bg-dark p-6 rounded-lg">
  <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
  <span className="inline-block bg-gradient-to-r from-gray-800 via-gray-900 to-black bg-clip-text text-transparent text-2xl sm:text-3xl font-bold lg:ml-[300px] ">
      then.... 
    </span>

    <div className="bg-[#0A0A0A] p-6 rounded-lg shadow-lg">
      <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl font-bold">
       View your repos in tree structure
      </span>
      <p className="mt-4 text-gray-300">
        Easily manage and access your projects with custom collections.
      </p>
    </div>

    <div className="bg-[#0A0A0A] p-6 rounded-lg shadow-lg">
      <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl font-bold">
        Analyze lines of code
      </span>
      <p className="mt-4 text-gray-300">
        Keep your workflow streamlined and efficient with grouped repositories.
      </p>
    </div>
    <span className="inline-block bg-gradient-to-r from-gray-800 via-gray-900 to-black bg-clip-text text-transparent text-2xl sm:text-3xl font-bold lg:mt-[140px]">
      and much more...
    </span>
    
  </div>
</div>
 </div> 
 </>
    )
}