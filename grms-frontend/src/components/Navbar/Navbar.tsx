"use client";

import type {NavbarProps} from "@nextui-org/react";

import React from "react";
import axios from "axios";

import {  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  NavbarMenu,
  NavbarMenuItem,
  NavbarMenuToggle,
  Link,
  Button,
  Divider,
  DropdownItem,
  DropdownTrigger,
  Dropdown,
  DropdownMenu,
  Avatar,
} from "@nextui-org/react";
import {Icon} from "@iconify/react";
import {cn} from "@nextui-org/react";
import Logo from "../../assets/images/FinalLogo.png"

import { useNavigate } from "react-router-dom";



const menuItems = [
  "About",
  "Contribute",
  "Features",
  "Changelog",
  "Buy Me a Cup Of Coffee",
  "Contact Me",
];

//Navbar Before Authorization.
export const NavbarComponent = (props: NavbarProps) => {
    let backendUrl = import.meta.env.VITE_BACKEND_URL + "/oauth2/authorization/github"
  const [isMenuOpen, setIsMenuOpen] = React.useState(false);

  return (
    <Navbar 
    
      {...props}
      classNames={{
        base: cn("border-default-100 rounded", {
          "bg-default-200/50 dark:bg-default-100/50": isMenuOpen,
        }),
        wrapper: "w-full justify-center",
        item: "hidden md:flex",
        
      }}
      height="60px"
      isMenuOpen={isMenuOpen}
      onMenuOpenChange={setIsMenuOpen}
    >
      {/* Left Content */}
      
      <NavbarBrand >
        <div className="rounded-full w-12 bg-foreground  ">
          <img src = {Logo} alt="Logo"/>
        </div>
        <span className="ml-2 text font-medium">EasyRepo</span>
      </NavbarBrand>

      {/* Center Content */}
      <NavbarContent justify="center">
        <NavbarItem>
          <Link className="text-default-500" href="/" size="sm">
            Home
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link className="text-default-500" href="#" size="sm">
            Features
          </Link>
        </NavbarItem>
        <NavbarItem isActive>
          <Link aria-current="page" color="foreground" href="#" size="sm">
            Contribute
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link className="text-default-500" href="#" size="sm">
            About 
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link className="text-default-500" href="#" size="sm">
            Feedback
          </Link>
        </NavbarItem>
      </NavbarContent>

      {/* Right Content */}
      <NavbarContent className="hidden md:flex" justify="end">
        
        <NavbarItem className="ml-2 !flex gap-2">
            <Link href = "/login">
          <Button className="text-default-500" radius="full" variant="light">
            Login
          </Button>
          </Link>

          <Link href = {backendUrl}>
          <Button
            className="bg-foreground font-medium text-background"
            color="secondary"
            endContent={<Icon icon="solar:alt-arrow-right-linear" />}
            radius="full"
            variant="flat"
          >
            One Tap Login
          </Button>
          </Link>
        </NavbarItem>
      </NavbarContent>

      <NavbarMenuToggle className="text-default-400 md:hidden" />

      <NavbarMenu className="top-[calc(var(--navbar-height)_-_1px)] max-h-fit bg-default-200/50 pb-6 pt-6 shadow-medium backdrop-blur-md backdrop-saturate-150 dark:bg-default-100/50">
        <NavbarMenuItem>
          <Button fullWidth as={Link} href="/#" variant="faded">
            Sign In
          </Button>
        </NavbarMenuItem>
        <NavbarMenuItem className="mb-4">
          <Button fullWidth as={Link} className="bg-foreground text-background" href="/#">
            Get Started
          </Button>
        </NavbarMenuItem>
        {menuItems.map((item, index) => (
          <NavbarMenuItem key={`${item}-${index}`}>
            <Link className="mb-2 w-full text-default-500" href="#" size="md">
              {item}
            </Link>
            {index < menuItems.length - 1 && <Divider className="opacity-50" />}
          </NavbarMenuItem>
        ))}
      </NavbarMenu>
    
    </Navbar>
  );
}


//Navbar After Authorization
interface NavbarAfterAuthProps{
    data:{
    masterId: number;
    username: string;
    email: string;
    avatarUrl: string;
    bio: string;
    emailVeriied: boolean;
    provider: string;
    providerUserId: string;
    enabled: string;
    }


}


export const NavbarAfterAuth = (props: NavbarAfterAuthProps) => {
  const navigate = useNavigate()

  const backendUrl = import.meta.env.VITE_BACKEND_URL

const logoutFunction = async() =>{
    try{
      console.log('hello')
   await axios.post(backendUrl + '/logout' , {}, {withCredentials: true , headers: {'X-CSRF-TOKEN' : sessionStorage.getItem('csrf') }})
   sessionStorage.removeItem('authenticated')
   navigate("/")
   window.location.reload();
  
    }

    catch(error){

    }
}

  return (
    <Navbar className="">
     <NavbarBrand >
        <div className="rounded-full w-12 bg-foreground  ">
          <img src = {Logo} alt="Logo"/>
        </div>
        <span className="ml-2 text font-medium" style={{
          backgroundImage: 'linear-gradient(150deg, #fff000 30%, #ed008c 70%)',
          WebkitBackgroundClip: 'text',
          color: 'transparent'
        }}>EasyRepo</span>
      </NavbarBrand>

      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        <NavbarItem>
          <Link color="foreground" href="#">
            Features
          </Link>
        </NavbarItem>
        <NavbarItem isActive>
        <Link 
        aria-current="page" 
        href="#" 
        style={{
          color: '#ed008c'
        }}
        >


            Collections
          </Link>
        </NavbarItem>
        <NavbarItem>
          <Link color="foreground" href="#">
            How to CLI
          </Link>
        </NavbarItem>
      </NavbarContent>

      <NavbarContent as="div" justify="end">
        <Dropdown placement="bottom-end">
          <DropdownTrigger>
            <Avatar
              isBordered
              as="button"
              className="transition-transform"
              name="Jason Hughes"
              size="sm"
              color = "default"
              src={props.data?.avatarUrl}
            />
          </DropdownTrigger>
          <DropdownMenu aria-label="Profile Actions" variant="flat">
            <DropdownItem key="profile" className="h-14 gap-2">
              <p className="font-semibold">{props.data?.username}</p>
            </DropdownItem>
            <DropdownItem key="settings">My Settings</DropdownItem>
            <DropdownItem key="team_settings">Team Settings</DropdownItem>
            <DropdownItem key="analytics">Analytics</DropdownItem>
            <DropdownItem key="system">System</DropdownItem>
            <DropdownItem key="configurations">Configurations</DropdownItem>
            <DropdownItem key="help_and_feedback">Help & Feedback</DropdownItem>
            <DropdownItem key="logout" color="danger">
              <Button onPress={logoutFunction}>Log Out</Button>
            </DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </NavbarContent>
    </Navbar>
  );
}




