import './Style-SignUp.css'
import { useAuth } from '../../hooks/useAuth'
import { useState } from 'react'

const SignUp = ({onSwitch}) => {

  const [formSignUpData, setFormSignUpData] = useState({
    fullname: '',
    username: '',
    email: '',
    phoneNumber: '',
    dob: '',
    password: '',
    confirmPassword: ''
  })

  const [errors, setErrors] = useState({})

  const { register } = useAuth()

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormSignUpData(prev => ({
      ...prev,
      [name]: value
    }))
  }

  const validateForm = () => {
    const newErrors = {}
    if (!formSignUpData.fullname.trim()) {
      newErrors.fullname = "fullname is required"
    }
    if (!formSignUpData.username.trim()) {
      newErrors.username = "username is required"
    }
    if (!formSignUpData.email.trim()) {
      newErrors.email = "email is required"
    }
    if (!formSignUpData.password.trim()) {
      newErrors.password = "password is required"
    }
    if (!formSignUpData.confirmPassword.trim()) {
      newErrors.confirmPassword = "confirm password is required"
    } else if (formSignUpData.password.trim() !== formSignUpData.confirmPassword.trim()) {
      newErrors.confirmPasswordMatch = "passwords do not match"
    }
    return newErrors;
  }

  const handleSignUpSubmit = async (e) => {
    e.preventDefault()
    const newErrors = validateForm()
    setErrors(newErrors)
    if (Object.keys(newErrors).length > 0) {
      return 
    }
    try {
      console.log(formSignUpData)
      await register(formSignUpData)
    } catch (error) {
      console.log("Sign up failed", error)
    }
  }

  return (
    <form className="signUpForm" onSubmit={handleSignUpSubmit}>
      <h1>Sign Up</h1>
      <div>
        <input 
          type="text" 
          placeholder='Fullname'
          name='fullname'
          value={formSignUpData.fullname}
          onChange={handleChange}
        />
        {
          errors.fullname &&
          <p>{errors.fullname}</p>
        }
      </div>
      <div>
        <input 
          type="text" 
          placeholder='Username'
          name='username'
          value={formSignUpData.username}
          onChange={handleChange}
        />
        {
          errors.username &&
          <p>{errors.username}</p>
        }
      </div>
      <div>
        <input 
          type="text" 
          placeholder='Email'
          name='email'
          value={formSignUpData.email}
          onChange={handleChange}
        />
        {
          errors.username &&
          <p>{errors.email}</p>
        }
      </div>
      <div>
        <input 
          type="text" 
          placeholder='phoneNumber'
          name='phoneNumber'
          value={formSignUpData.phoneNumber}
          onChange={handleChange}
        />
      </div>
      <div>
        <input 
          type="text" 
          placeholder='Date of birth'
          name='dob'
          value={formSignUpData.dob}
          onChange={handleChange}
        />
      </div>
      <div>
        <input 
          type="password" 
          placeholder='Choose Password'
          name='password'
          value={formSignUpData.password}
          onChange={handleChange}
        />
        {
          errors.username &&
          <p>{errors.password}</p>
        }
      </div>
      <div>
        <input 
          type="password" 
          placeholder='Confirm Password'
          name='confirmPassword'
          value={formSignUpData.confirmPassword}
          onChange={handleChange}
        />
        {
          errors.confirmPassword &&
          <p>{errors.confirmPassword}</p>
        }
        {
          errors.confirmPasswordMatch &&
          <p>{errors.confirmPasswordMatch}</p>
        }
      </div>
      
      <button>Sign Up</button>
      <p>
        Already a account?
        <span onClick={onSwitch}> Login</span>
      </p>
    </form>
  )
}

export default SignUp