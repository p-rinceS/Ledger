
import {
  BrowserRouter,
  Route,
  Routes,
} from "react-router-dom";

import Home from "./pages/Home";
import Profile from "./pages/Profile";
import Contact from "./pages/Contact";
import NoPage from "./pages/NoPage";




function App() {

  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
            <Route index  element={<Home />} />
            <Route index path="/home" element={<Home />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="*" element={<NoPage />} />
          </Routes>
        </BrowserRouter>
      </div>
      
    </>
  )
}

export default App
