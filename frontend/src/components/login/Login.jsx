import { useState } from 'react'
import './Style-Login.css'
import { useAuth } from '../../hooks/useAuth'

const Login = ({onSwitch}) => {

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState(null)
  const { login } = useAuth()

  const handleSubmit = async (e) => {
    e.preventDefault()

    try {
      await login(username, password)
    } catch (err) {
      console.error('Login failed', err)
      setError(true)
    }
  }

  return (
    <form className="formLogin" onSubmit={handleSubmit}>
      <h1>TNChat App</h1>
      <p className='p1'>Log in to your account</p>
      <input 
        type="text" 
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        required
      />
      <input 
        type="password" 
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      />
      <p className='forgot-password'>Forgot password</p>
      <button type='submit'>Log In</button>
      {
        error && 
        <p className='incorrect'>Incorrect username or password</p>
      }
      <p className='p2'>
        Don't have an account?
        <span onClick={onSwitch}> Sign up</span>
      </p>
    </form>
  )
}

export default Login