import { useState, useCallback } from 'react';
import { fenggeshilie } from '../services/homeApi';

export default function useAuthModel() {
  // const [msg, setmsg] = useState(null)

  const homepage = async (values) => {
    const homedata = await fenggeshilie();
    return homedata;
  };

  return {
    homepage,
  };
}
