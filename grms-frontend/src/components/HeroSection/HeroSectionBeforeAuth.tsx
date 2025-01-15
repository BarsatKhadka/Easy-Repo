import CreateCollections from "../../../src/assets/images/CreateCollections.png"
export const HeroSectionBeforeAuth = () => {


  return (
    <>
    <section className="fade-bottom  pb-0 sm:pb-0 md:pb-0 mr-4 lg:mr-96 lg:ml-96 mt-2">
      <div className=" flex flex-col gap-12 pt-16 sm:gap-24 ">
        <div className="flex flex-col items-center gap-6 text-center sm:gap-12">
          <h1 className="relative z-10 inline-block animate-appear bg-gradient-to-r from-foreground to-muted-foreground bg-clip-text text-3xl font-semibold  text-transparent drop-shadow-2xl sm:text-6xl sm:leading-tight md:text-8xl ">
          Your All-in-One Repository Solution.
          </h1>
        </div>
      </div>
    </section>
    <div className="mt-16 flex justify-center items-center  flex-col items-start space-y-8 px-4 sm:px-8 md:px-12 lg:px-16">
  <span className="inline-block bg-gradient-to-r from-blue-500 to-green-500 bg-clip-text text-transparent text-2xl sm:text-3xl md:text-4xl lg:text-5xl font-bold">
    Group your Repositories in Collections
  </span>
  <img 
    src={CreateCollections} 
    alt="Create Collections Illustration" 
    className="w-full max-w-4xl h-auto inline"
  />
</div>







  
3
    </>
    
  );
}