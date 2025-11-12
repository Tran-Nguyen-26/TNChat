import { createContext, useState } from "react"
import { BrowserRouter, Router, Routes, Route, Navigate} from "react-router-dom"
import './App.css'
import Home from "./pages/home/Home"
import Auth from "./pages/auth/Auth"

const MyContext = createContext()

function App() {

  return (
    // <MyContext>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to="/auth"/>}/>
          <Route path="/auth" element={<Auth/>}/>
          <Route path="/home" element={<Home/>}/>
        </Routes>
      </BrowserRouter>
    // </MyContext>
  )
}

export default App
