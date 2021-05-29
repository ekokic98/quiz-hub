import React, { useEffect, useState } from 'react';
import { InboxOutlined } from "@ant-design/icons";
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";
import { LoadingOutlined } from '@ant-design/icons';
import { Spin, message } from "antd";
import { getAllFavoritesByUser } from "api/property/favorite";
import { getUser } from "utilities/localStorage";

const FavoritesTab = () => {

    const history = useHistory();
    const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;
    const [quizzes, setQuizzes] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                setQuizzes(await getAllFavoritesByUser(getUser().username));
                setLoading(false);
            } catch (error) {
                setLoading(false);
                message.warning(error.response.data.message);
            }
        }

        fetchData();
    }, [])

    return (
        <div className='second-tab'>
            { loading && <Spin indicator={antIcon} /> }
            { !loading && quizzes?.length ?
                quizzes.map(quiz =>
                    <MyCard
                        key={ quiz.id }
                        onClick={ () => history.push(quizUrl + "/" + quiz.id) }
                        imgSrc={ quiz?.category?.imageUrl }
                        title={ quiz.name }
                        style={{width:200, marginBottom:5, height:220}}
                    />
                )
                :
                <div className='no-content'>
                    <InboxOutlined/>
                    <h2>You have no favorites yet!</h2>
                </div>
            }
        </div>
    )
}

export default FavoritesTab;