import React from 'react';
import { FaReddit, FaDiscord, FaGithub } from 'react-icons/fa';

export const Footer = () => {
  return (
    <>
      <div className="bg-[#0A0A0A] text-white py-8">
        <div className="container mx-auto flex justify-center space-x-6">
          <a
            href="https://www.reddit.com/r/EasyRepo/"
            target="_blank"
            rel="noopener noreferrer"
            className="text-white hover:text-gray-400 transition duration-300"
          >
            <FaReddit size={24} />
          </a>
          <a
            href="https://discord.gg/jHD927vCF3"
            target="_blank"
            rel="noopener noreferrer"
            className="text-white hover:text-gray-400 transition duration-300"
          >
            <FaDiscord size={24} />
          </a>
          <a
            href="https://github.com/BarsatKhadka/Easy-Repo"
            target="_blank"
            rel="noopener noreferrer"
            className="text-white hover:text-gray-400 transition duration-300"
          >
            <FaGithub size={24} />
          </a>
        </div>
      </div>
    </>
  );
};