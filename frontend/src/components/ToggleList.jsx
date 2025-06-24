import { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
import api from "../api";
import {
  Button,
  CommentBox, CreateButton, Input,
  ModalBox,
  ModalOverlay,
  PageContainer,
  Switch,
  ToggleBox,
} from "./StyledComponents";

export default function ToggleList() {
  const navigate = useNavigate();
  const [flags, setFlags] = useState([]);
  const [selectedFlag, setSelectedFlag] = useState(null);
  const [comment, setComment] = useState("");
  const [showConfirm, setShowConfirm] = useState(false);

  useEffect(() => {
    fetchFlags();
  }, []);

  const fetchFlags = async () => {
    const res = await api.get("/");
    const sortedFlags = res.data.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
    setFlags(sortedFlags);
  };

  const handleRadioClick = (flag) => {
    setSelectedFlag(flag);
    setComment("");
    setShowConfirm(true);
  };

  const handleConfirm = async () => {
    try {
      await api.patch(`/update/${selectedFlag.flagKey}`, {
        isEnabled: !selectedFlag.isEnabled,
        comment: comment,
      });
      setShowConfirm(false);
      setSelectedFlag(null);
      fetchFlags();
    } catch (err) {
      console.error(err);
      alert("Failed to update flag");
    }
  };

  return (
      <>
        <PageContainer blurred={showConfirm}>
          <div className="flex justify-between items-center mb-4">
            <h1 className="text-2xl font-bold">Feature Toggles</h1>
            <CreateButton onClick={() => navigate("/new")}>
              + New Toggle
            </CreateButton>
          </div>
          <ul className="space-y-3">
            {flags.map((flag) => (
                <ToggleBox key={flag.flagKey}>
                  <div>
                    <p>
                      <strong>{flag.name}</strong> ({flag.flagKey})
                    </p>
                    <p className="text-sm text-gray-500">
                      {flag.type} | Created by {flag.createdBy}
                    </p>
                  </div>
                  <label className="flex items-center space-x-2">
                    <Input checked={flag.isEnabled} type="checkbox" onChange={() => handleRadioClick(flag)} />
                    <Switch />
                  </label>

                </ToggleBox>
            ))}
          </ul>
        </PageContainer>

        {showConfirm && selectedFlag && (
            <ModalOverlay>
              <ModalBox>
                <h2 className="text-lg font-semibold mb-4">Confirm Toggle Change</h2>
                <p>
                  Are you sure you want to{" "}
                  {selectedFlag.isEnabled ? "disable" : "enable"}{" "}
                  <strong>{selectedFlag.name}</strong>?
                </p>
                <CommentBox
                    placeholder="Add a comment"
                    value={comment}
                    onChange={(e) => setComment(e.target.value)}
                    rows={3}
                />
                <div className="flex justify-end space-x-2 mt-4">
                  <Button onClick={() => setShowConfirm(false)}>Cancel</Button>
                  <Button onClick={handleConfirm} variant="confirm">
                    Confirm
                  </Button>
                </div>
              </ModalBox>
            </ModalOverlay>
        )}
      </>
  );
}
