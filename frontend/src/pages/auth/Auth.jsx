import { useState } from "react"
import Login from "../../components/login/Login"
import SignUp from "../../components/signup/SignUp"
import './Style-Auth.css'

const Auth = () => {

  const [authMode, setAuthMode] = useState('login')

  const switchMode = () => {
    setAuthMode(authMode === 'login' ? 'signup' : 'login')
  }

  return (
    <div className="auth-container">
      {
        authMode === "login" ? 
          (<Login onSwitch={switchMode}/>) :
          (<SignUp onSwitch={switchMode}/>)
      }
    </div>
  )
}

export default Auth