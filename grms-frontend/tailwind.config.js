/** @type {import('tailwindcss').Config} */
const { nextui } = require("@nextui-org/react");

export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}"
  ],
  theme: {
    extend: {},
  },
  darkMode: "class",
  plugins: [
    nextui({
      themes: {
        dark: {
          colors: {
            background: "#0A0A0A", 
            foreground: "#FFFFFF", 
            primary: {
              50: "#F0F9FF",
              100: "#E0F2FE",
              200: "#BAE6FD",
              300: "#7DD3FC",
              400: "#38BDF8",
              500: "#0EA5E9", 
              600: "#0284C7",
              700: "#0369A1",
              800: "#075985",
              900: "#0C4A6E",
              DEFAULT: "#0EA5E9", 
              foreground: "#FFFFFF", 
            },
            secondary: {
              50: "#F5F3FF",
              100: "#EDE9FE",
              200: "#DDD6FE",
              300: "#C4B5FD",
              400: "#A78BFA",
              500: "#8B5CF6", 
              600: "#7C3AED",
              700: "#6D28D9",
              800: "#5B21B6",
              900: "#4C1D95",
              DEFAULT: "#8B5CF6", 
              foreground: "#FFFFFF", 
            },
            success: {
              50: "#ECFDF5",
              100: "#D1FAE5",
              200: "#A7F3D0",
              300: "#6EE7B7",
              400: "#34D399",
              500: "#10B981", 
              600: "#059669",
              700: "#047857",
              800: "#065F46",
              900: "#064E3B",
              DEFAULT: "#10B981", 
              foreground: "#FFFFFF", 
            },
            warning: {
              50: "#FFFBEB",
              100: "#FEF3C7",
              200: "#FDE68A",
              300: "#FCD34D",
              400: "#FBBF24",
              500: "#F59E0B", 
              600: "#D97706",
              700: "#B45309",
              800: "#92400E",
              900: "#78350F",
              DEFAULT: "#F59E0B", 
              foreground: "#FFFFFF", 
            },
            danger: {
              50: "#FEF2F2",
              100: "#FEE2E2",
              200: "#FECACA",
              300: "#FCA5A5",
              400: "#F87171",
              500: "#EF4444", 
              600: "#DC2626",
              700: "#B91C1C",
              800: "#991B1B",
              900: "#7F1D1D",
              DEFAULT: "#EF4444", 
              foreground: "#FFFFFF", 
            },
            
            focus: "transparent", 
          },
          layout: {
            disabledOpacity: "0.3", 
            radius: {
              small: "4px",
              medium: "6px",
              large: "8px",
            },
            borderWidth: {
              small: "1px",
              medium: "2px",
              large: "3px",
            },
          },
        },
      },
    }),
  ],
};