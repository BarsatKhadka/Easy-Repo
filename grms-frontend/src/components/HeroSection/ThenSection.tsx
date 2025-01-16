import { Button } from "@nextui-org/react"
import LinesOfCode from "../../../src/assets/images/LinesOfCode.png"
import Tree from "../../../src/assets/images/Tree.png"
import {Icon} from "@iconify/react";
import { FaGithub } from "react-icons/fa";

export const ThenSection = () =>{
    return(
<>
<div className="bg-white">
<div className="mt-4 flex justify-center items-center  flex-col items-start space-y-8 px-4 sm:px-8 md:px-12 lg:px-16 ">
<span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl md:text-4xl lg:text-5xl font-bold ">
    Then?   
</span>
</div>
<div className="bg-dark p-6 rounded-lg">
  <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">

    <span className="inline-block bg-gradient-to-r from-gray-800 via-gray-900 to-black bg-clip-text text-transparent text-2xl sm:text-3xl font-bold lg:ml-[300px]">
      then.... 
    </span>


    <div className="bg-[#1C1917] p-5 rounded-lg shadow-lg border-2 border-pink-500 hover:shadow-[0_0_20px_5px_rgba(236,72,153,0.6)] transition-all duration-500 ease-in-out transform hover:scale-102 cursor-pointer">
      <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl font-bold">
        View your repos in tree structure
      </span>
      <p className="mt-4 text-gray-300">
      Visualize your repositories in an organized tree structure for better navigation and clarity.
      </p>
      <img src={Tree} className="border border-white mt-4 rounded-lg hover:border-pink-500 transition-all duration-300"/>
    </div>


    <div className="bg-[#1C1917] p-5 rounded-lg shadow-lg border-2 border-purple-500 hover:shadow-[0_0_20px_5px_rgba(168,85,247,0.6)] transition-all duration-500 ease-in-out transform hover:scale-102 cursor-pointer">
      <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl sm:text-3xl font-bold">
        Analyze lines of code
      </span>
      <p className="mt-12 text-gray-300">
        Gain insights into your codebase by analyzing lines of code and tracking progress.
      </p>
      <img src={LinesOfCode} className="border border-white mt-4 rounded-lg hover:border-purple-500 transition-all duration-300 lg:mt-[20px]"/>
    </div>


<div>
    <span className="inline-block bg-gradient-to-r from-gray-800 via-gray-900 to-black bg-clip-text text-transparent text-2xl sm:text-3xl font-bold lg:mt-[520px]  ">
      and much more...   <Button
                          className="bg-black font-medium text-white" // Black background with white text
                          color="secondary"
                          endContent={<Icon icon="solar:alt-arrow-right-linear" />}
                          radius="full"
                          variant="flat"
                        ><FaGithub />Explore All possibilities</Button>
    </span>
    </div>
    
  </div>
</div>
 </div> 
 </>
    )
}