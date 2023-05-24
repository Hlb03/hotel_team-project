const SET_USER = "SET_USER"

const initialState = {
    dataUser: {}
}

export const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_USER:
            return {
                
                dataUser: action.payload
            }
        default:
            return state;
    }
}

export const setUserAction = (payload) => {
    return {
        type: SET_USER,
        payload
    }
}