import { MdAccountCircle } from "react-icons/md"
import { useFriend } from "../../hooks/useFriend"
import { useEffect, useState } from "react"
import './Style-Friends.css'
import { IoMdMore } from "react-icons/io"

const Friends = () => {

  const { getFriends } = useFriend()
  const [friends, setFriends] = useState([])

  useEffect(() => {
    const fetchFriends = async () => {
      try {
        const pageFriends = await getFriends(0, 20)
        setFriends(pageFriends.data)
      } catch (error) {
        console.log("Get friends list failed: ", error)
      }
    }
    fetchFriends()
  }, [getFriends])
  

  return (
    <div className="friends">
      {
        friends.map(f => { return (
          <div 
            className="friend"
            key={f.id}
          >
            <div>
              <MdAccountCircle/>
              <span>{f.username}</span>
            </div>
            <IoMdMore/>
          </div>
        )})
      }
      
    </div>
  )
}

export default Friends