import { friendService } from "../services/friendService";

export const useFriend = () => {
  const getFriends = async (page, size) => {
    try {
      const data = await friendService.getFriends(page, size)
      return data
    } catch (err) {
      throw err
    }
  }
  return {getFriends}
}