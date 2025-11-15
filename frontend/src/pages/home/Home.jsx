import { useState } from 'react'
import './Style-Home.css'
import { FaUserFriends } from "react-icons/fa"
import { IoSettingsSharp } from "react-icons/io5"
import { IoSearch } from "react-icons/io5"
import Conversations from '../../components/conversations/Conversations'
import Friends from '../../components/friends/Friends'

const Home = () => {

    const [showConversations, setConversations] = useState(true);

  return (
    <div className='home'>
      <div className='left'>
        <div className='header'>
          <h2>Đoạn chat</h2>
          <div className='h-choice'>
            <div>
              <FaUserFriends onClick={() => setConversations(!showConversations)}/>
            </div>
            <div>
              <IoSettingsSharp/>
            </div>
          </div>
          
        </div>
        <div className='search'>
          <IoSearch/>
          <input type="text" placeholder={`${showConversations ? "Tìm kiếm đoạn chat" : "Danh bạ"}`}/>
        </div>
        {
          showConversations ? <Conversations/> : <Friends/>
        }
        
      </div>



      <div className='mid'>mid</div>
      <div className='right'>right</div>
    </div>
  )
}

export default Home