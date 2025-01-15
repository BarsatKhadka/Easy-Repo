export const ThenSection = () =>{
    return(
<>
<div className="bg-white">
<div className="mt-4 flex justify-center items-center  flex-col items-start space-y-8 px-4 sm:px-8 md:px-12 lg:px-16 ">
<span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl md:text-4xl lg:text-5xl font-bold">
    Then?   
</span>
</div>

<div className="flex mt-10 justify-center bg-white">
<div className="rounded-lg w-1/3 border border-gray-700 shadow-lg shadow-black mx-2 my-4 p-4 border-white bg-black-800"> 
{/* <img 
        src={flashCard} 
        alt="Flashcard" 
        className="rounded-t-lg w-full h-48 object-cover mb-4" 
      /> */}
      <h3 className="text-lg font-bold text-orange-400">Quizzer</h3>
      <p className="text-gray-300">This is a brief description of the second card.</p>
     
    </div>
    <div className="rounded-lg w-1/3 border border-gray-700 shadow-lg shadow-black mx-2 my-4 p-4 border-white bg-black-800"> 
{/* <img 
        src={flashCard} 
        alt="Flashcard" 
        className="rounded-t-lg w-full h-48 object-cover mb-4" 
      /> */}
      <h3 className="text-lg font-bold text-orange-400">Quizzer</h3>
      <p className="text-gray-300">This is a brief description of the second card.</p> 
     
    </div>

 </div>  
 </div> 
 </>
    )
}