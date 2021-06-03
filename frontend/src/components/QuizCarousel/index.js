import React from 'react';
import Carousel, { autoplayPlugin  } from '@brainhubeu/react-carousel';
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";

import '@brainhubeu/react-carousel/lib/style.css';
import "./quizCarousel.scss";

const QuizCarousel = ({ quizzes }) => {
    const history = useHistory();

    return (
        <div className="quiz-carousel-container">
            <Carousel plugins={[
                    'infinite',
                    'arrows',
                    {
                        resolve: autoplayPlugin,
                        options: {
                            interval: 4000,
                        }
                    }
                ]}
            >
                {quizzes.map(quiz => (
                    <div key={quiz.id} className="quiz-carousel">
                        <MyCard
                            key={quiz.id}
                            onClick={() => history.push( quizUrl + "/" + quiz.id)}
                            imgSrc={quiz?.category?.imageUrl}
                            title={quiz.name}
                        />
                    </div>
                ))}
            </Carousel>
        </div>
    );
}

export default QuizCarousel;
