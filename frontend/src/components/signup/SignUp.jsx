import './Style-SignUp.css'

const SignUp = ({onSwitch}) => {
  return (
    <form className="signUpForm">
      <h1>Sign Up</h1>
      <div>
        <input type="text" placeholder='Fullname'/>
      </div>
      <div>
        <input type="text" placeholder='Username'/>
      </div>
      <div>
        <input type="text" placeholder='Email'/>
      </div>
      <div>
        <input type="text" placeholder='phoneNumber'/>
      </div>
      <div>
        <input type="text" placeholder='Date of birth'/>
      </div>
      <div>
        <input type="password" placeholder='Choose Password'/>
      </div>
      <div>
        <input type="password" placeholder='Confirm Password'/>
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