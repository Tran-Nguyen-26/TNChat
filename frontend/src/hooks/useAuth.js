import { authService } from "../services/authService"

export const useAuth = () => {
  const login = async (username, password) => {
    try {
      const jwtResponse = await authService.login(username, password)
      window.location.href = '/home'
      return jwtResponse
    } catch (err) {
      throw err
    }
  }

  return { login }
}