import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

//import Header from '../Header';

const Layout = ({children}) => {
    return (
        <Fragment>

            <main>{children}</main>
        </Fragment>
    );
};

Layout.propTypes = {
    children: PropTypes.node
};

export default Layout;
/*            <Header/> */