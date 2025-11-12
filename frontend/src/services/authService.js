import apiCall from '../utils/api'

export const authService = {
  login : async (username, password) => {
    const response = await apiCall('/auth/login', {
      method: 'POST',
      body: JSON.stringify({ username, password})
    })

    const { token, id, username : serverUsername} = response.data

    localStorage.setItem('token', token)
    localStorage.setItem('user', JSON.stringify({
      id,
      username: serverUsername
    }))

    return response.data
  },

  register : async (userRegisterRequest) => {
    const response = await apiCall('/auth/signup', {
      method: 'POST',
      body: JSON.stringify(userRegisterRequest)
    })

    const { token, id, username} = response.data

    localStorage.setItem('token', token)
    localStorage.setItem('user', JSON.stringify({
      id,
      username
    }))

    return response.data
  }
}