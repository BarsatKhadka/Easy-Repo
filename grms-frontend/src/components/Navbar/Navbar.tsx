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
  User,
} from "@nextui-org/react";
import {Icon} from "@iconify/react";
import {cn} from "@nextui-org/react";
import Logo from "../../assets/images/FinalLogo.png"

import { useNavigate } from "react-router-dom";





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
        <span className="ml-2 text font-medium"><a href="/">EasyRepo</a></span>
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

      <NavbarMenu className="top-[calc(var(--navbar-height)_-_1px)] max-h-fit pb-6 pt-6 shadow-medium backdrop-blur-md backdrop-saturate-150 dark:bg-default-100/50">
        <NavbarMenuItem>
          <Button fullWidth as={Link} href="/login" variant="faded">
            Log in
          </Button>
        </NavbarMenuItem>
        <NavbarMenuItem className="mb-4">
          <Button fullWidth as={Link} className="bg-foreground text-background" href="/#">
            Home
          </Button>
        </NavbarMenuItem>
        
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

   await axios.post(backendUrl + '/logout' , {}, {withCredentials: true , headers: {'X-CSRF-TOKEN' : sessionStorage.getItem('csrf') }})
   sessionStorage.removeItem('authenticated')
   navigate("/")
   window.location.reload();
  
    }

    catch(error){

    }
}

  return (
    <Navbar className="navbar">
     <NavbarBrand >
        <div className="rounded-full w-12 bg-foreground  ">
          <img src = {Logo} alt="Logo"/>
        </div>
        <span className="ml-2 text font-medium" style={{
          backgroundImage: 'linear-gradient(150deg, #fff000 30%, #ed008c 70%)',
          WebkitBackgroundClip: 'text',
          color: 'transparent',
          textShadow: `
      0.7px 0.7px 0.7px #ffffff`
        }}><a href="/">EasyRepo</a></span>
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
          color: '#ffffff'
        }}
        >


            Collections
          </Link>
        </NavbarItem>
        <NavbarItem>
          <a href="#">

            How to CLI
            </a>
        </NavbarItem>
      </NavbarContent>

      <NavbarContent as="div" justify="end">
      <Dropdown placement="bottom-start">
        <DropdownTrigger>
          <User
            as="button"
            avatarProps={{
              isBordered: true,
              src: props?.data?.avatarUrl,
            }}
          
            className="transition-transform"
            description= {props?.data?.email}
            name={props?.data?.username}
          />
        </DropdownTrigger>
        <DropdownMenu aria-label="User Actions" variant="flat">
          <DropdownItem key="profile" className="h-14 gap-2">
            <p className="font-bold">Signed in as</p>
            <p className="font-bold">{props?.data?.username}</p>
          </DropdownItem>
          <DropdownItem key="logout" color="danger">
              <Button onPress={logoutFunction}>Log Out</Button>
            </DropdownItem>
        
        </DropdownMenu>
      </Dropdown>
      </NavbarContent>
    </Navbar>
  );
}




