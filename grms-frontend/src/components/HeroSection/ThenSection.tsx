import { Button } from "@nextui-org/react";
import LinesOfCode from "../../../src/assets/images/LinesOfCode.png";
import Tree from "../../../src/assets/images/Tree.png";
import { Icon } from "@iconify/react";
import { FaGithub } from "react-icons/fa";

export const ThenSection = () => {
  return (
    <div className="bg-white py-12">
      <div className="container mx-auto px-4 sm:px-6 lg:px-8">

        <div className="text-center mb-12">
          <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-4xl sm:text-5xl font-bold">
            Then?
          </span>
        </div>


        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">

          <div className="bg-[#1C1917] p-6 rounded-lg border border-gray-800 hover:border-pink-500 transition-all duration-300 cursor-pointer">
            <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl font-bold">
              View your repos in tree structure
            </span>
            <p className="mt-3 text-gray-300">
              Visualize your repositories in an organized tree structure for better navigation and clarity.
            </p>
            <img
              src={Tree}
              alt="Tree Structure"
              className="mt-6 rounded-md border border-gray-700 hover:border-pink-500 transition-all duration-300 w-full"
            />
          </div>


          <div className="bg-[#1C1917] p-6 rounded-lg border border-gray-800 hover:border-purple-500 transition-all duration-300 cursor-pointer">
            <span className="inline-block bg-gradient-to-r from-pink-500 to-purple-500 bg-clip-text text-transparent text-2xl font-bold">
              Analyze lines of code
            </span>
            <p className="mt-3 text-gray-300">
              Gain insights into your codebase by analyzing lines of code and tracking progress.
            </p>
            <img
              src={LinesOfCode}
              alt="Lines of Code"
              className="mt-6 rounded-md border border-gray-700 hover:border-purple-500 transition-all duration-300 w-full"
            />
          </div>


          <div className="lg:mt-[435px] mr-[160px] rounded-lg  hover:border-indigo-500 transition-all duration-300 cursor-pointer flex flex-col justify-center items-center">
            <span className="inline-block bg-gradient-to-r from-indigo-500 to-purple-500 bg-clip-text text-transparent text-2xl font-bold text-center">
              And much more...
            </span>
            <Button
              className="mt-6 bg-gradient-to-r from-indigo-500 to-purple-500 text-white font-medium hover:from-indigo-600 hover:to-purple-600 transition-all duration-300"
              endContent={<Icon icon="solar:alt-arrow-right-linear" />}
              radius="full"
              variant="solid"
            >
              <FaGithub className="mr-2" />
              Explore All Possibilities
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};