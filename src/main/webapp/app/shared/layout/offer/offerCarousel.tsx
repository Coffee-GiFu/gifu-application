
import React, { Component } from 'react';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

export interface OfferCarouselProps {
    pictures: Blob[]
    autoPlay?: boolean
}

export const OfferCarousel = ({ pictures, autoPlay = false }: OfferCarouselProps) => {
    return (
        <Carousel autoPlay={autoPlay} infiniteLoop={true} stopOnHover={false} showArrows={false} showThumbs={false} showStatus={false}>
            {
                pictures.length === 0 && (
                    <div>
                        <img className="defaultImg" src="../../../../content/images/default.png" />
                    </div>
                )
            }
            <div>
                <img className="defaultImg" src="../../../../content/images/default.png" />
            </div>
            {
                pictures.map( pictureBlob => {
                    const imgSrc= URL.createObjectURL( pictureBlob );
                    (
                        <div>
                            <img src={imgSrc} />
                        </div>
                    )
                })
            }
        </Carousel>
    )
};
