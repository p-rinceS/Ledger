import { FC, useState } from "react";
import { Link } from "react-router-dom";
import "./NavigationBar.css";
import { Button } from "antd";
import { ConfigProvider } from "antd/lib";

import { CloseSquareFilled } from "@ant-design/icons";
import { AutoComplete } from "antd";

const gameOptions = [
  // get gane Image and name through API
  {
    value: "Ghost of Tsushima",
    label: (
      <div
        style={{
          justifyContent: "space-between",
          display: "flex",
          alignItems: "center",
        }}
      >
        <img
          src="./assets/ghost-of-tsushima.jpg"
          style={{ width: "50px", height: "75px" }}
        />
        <span style={{ marginLeft: "10px" }}>Ghost of Tsushima</span>
      </div>
    ),
  },
  { value: "God of War" },
  { value: "The Last of Us Part II" },
  { value: "Final Fantasy VII Remake" },
  { value: "Demon's Souls" },
  { value: "Persona 5 Royal" },
  { value: "Resident Evil Village" },
  { value: "Returnal" },
  { value: "Ratchet & Clank: Rift Apart" },
  { value: "Death Stranding" },
];

const NavigationBar: FC = () => {
  const [options, setOptions] = useState<{ value: string }[]>([]);

  return (
    <ConfigProvider wave={{ disabled: true }}>
      <div className="nav-bar">
        <Link to="/home">
          <img src="./assets/ledger-text-black.svg" className="ledger-icon" />
        </Link>
        <AutoComplete
          variant="outlined"
          options={options}
          style={{ width: 400 }}
          onSearch={(text) =>
            setOptions(
              gameOptions.filter((option) =>
                option.value.toLowerCase().includes(text.toLowerCase())
              )
            )
          }
          placeholder="Search"
          allowClear={{ clearIcon: <CloseSquareFilled /> }}
        ></AutoComplete>
        <Button ghost>
          <Link to="/profile">Profile</Link>
        </Button>
      </div>
    </ConfigProvider>
  );
};

export default NavigationBar;
