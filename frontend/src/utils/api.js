const API_URL = 'http://localhost:8080/api/v1'

const apiCall = async (endpoint, options = {}) => {
  const token = localStorage.getItem('token')
  const config = {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...options.headers
    }
  }

  try {
    const response = await fetch(`${API_URL}${endpoint}`, config)
    const data = await response.json()

    if (!response.ok) {
      throw { ...data, status: response.status}
    }
    return data
  } catch (error) {
    throw error
  }
}

export default apiCall