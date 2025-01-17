import { Accordion, AccordionItem } from "@nextui-org/react";


export const FAQSection = () => {
    return (
        <div className="bg-[#0A0A0A] flex flex-col items-center min-h-screen px-6 lg:py-[100px] ">
            <span className="bg-gradient-to-r from-blue-500 to-green-500 bg-clip-text text-transparent text-2xl sm:text-3xl md:text-4xl lg:text-5xl font-bold mb-8 mt-8">
                FAQ
            </span>
            
            <div className="w-full max-w-4xl mt-16 overflow-y-auto max-h-[80vh]">
                <Accordion variant="splitted" selectionMode="multiple" defaultExpandedKeys={["1","2","3","4"]}>
                    <AccordionItem key="1" aria-label="Accordion 1" title="Is this open source?" className="mb-4">
                        <div className="text-gray-300 max-h-48 overflow-y-auto">
                            Absolutely! This app <span className="text-green-500">is open source</span>, so feel free to poke around.
                        </div>
                    </AccordionItem>
                    <AccordionItem key="2" aria-label="Accordion 2" title="How can I add my dream features?" className="mb-4">
                        <div className="text-gray-300 max-h-48 overflow-y-auto">
                            If you're a web dev, congrats! You're officially invited to contribute and sprinkle your magic. If you're not a dev (or just feeling lazy), no worries—just log in and drop your requests in the feature request box. Easy peasy.
                        </div>
                    </AccordionItem>
                    <AccordionItem key="3" aria-label="Accordion 3" title="Do I need a GitHub account?" className="mb-4">
                        <div className="text-gray-300 max-h-48 overflow-y-auto">
                            YES, and I know, it’s tragic. The app revolves around GitHub, so OAuth with GitHub is the only login option. Blame the tech stack, not me.
                        </div>
                    </AccordionItem>
                    <AccordionItem key="4" aria-label="Accordion 4" title="What about GitLab or BitBucket? Any love for them?" className="mb-4">
                        <div className="text-gray-300 max-h-48 overflow-y-auto">
                            Nope, not this time. Maybe in the future when this project grows up and I’ve mastered DSA, aced interviews, and made some cash. Priorities, you know?
                        </div>
                    </AccordionItem>
                    <AccordionItem key="5" aria-label="Accordion 5" title="How can I support the creator? (Totally not a FAQ)" className="mb-4">
                        <div className="text-gray-300 max-h-48 overflow-y-auto">
                            Any support is golden. Buy me a coffee, and I’ll be forever grateful. Cheers!
                        </div>
                    </AccordionItem>
                </Accordion>
            </div>
        </div>
    );
};