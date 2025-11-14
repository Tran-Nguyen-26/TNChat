import './Style-Home.css'
import { FaUserFriends } from "react-icons/fa"
import { IoSettingsSharp } from "react-icons/io5"
import { IoSearch } from "react-icons/io5"

const Home = () => {
  return (
    <div className='home'>
      <div className='left'>
        <div className='header'>
          <h2>Đoạn chat</h2>
          <div className='h-choice'>
            <div>
              <FaUserFriends/>
            </div>
            <div>
              <IoSettingsSharp/>
            </div>
          </div>
          
        </div>
        <div className='search'>
          <IoSearch/>
          <input type="text" placeholder='Tìm kiếm đoạn chat'/>
        </div>
        <div className='groups-chat'></div>
      </div>



      <div className='mid'>mid</div>
      <div className='right'>right</div>
    </div>
  )
}

export default Home