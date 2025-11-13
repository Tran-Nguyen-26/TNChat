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

  const register = async (userData) => {
    try {
      const jwtResponse = await authService.register(userData)
      window.location.href = '/home'
      return jwtResponse
    } catch (error) {
      throw error
    }
  }

  return { login, register }
}