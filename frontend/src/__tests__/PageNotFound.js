import { configure, shallow } from "enzyme";
import PageNotFound from "../pages/PageNotFound";
import Adapter from "enzyme-adapter-react-16";
import { PageHeader } from "antd";

configure({adapter: new Adapter()});

it("Renders without crashing", () => {
    shallow(<PageHeader/>);
});

test("Page Not Found should contain 404 text", () => {
    const component = shallow(<PageNotFound/>);
    expect(component.find('.not-found-container').text()).toEqual("404Page not found");
});
