import apiCall from "../utils/api";

export const friendService = {
  getFriends : async (page, size) => {
    const response = await apiCall('/friends/all', {
      method: 'GET',
      params: { page, size }
    })

    return response.data;
  }
}